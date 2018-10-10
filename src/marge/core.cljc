(ns marge.core
  "The core namespace contains all of the functions for rendering clojure data structures as
  markdown.

  The API documentation is available [here](http://markwoodhall.github.io/marge/).

  You can also view [blog posts] (http://markw.xyz/tags/marge/) about marge
  "
  {:author "Mark Woodhall"}
  (:require [clojure.string :refer [join triml] :as s]
            [marge.util :refer [balance-at balance-when longest]]))

(declare pair->markdown list- ordered-list unordered-list)

(defonce ^:private linebreak "\n")
(defonce ^:private column-end " |")
(defonce ^:private divider "-")
(defonce ^:private whitespace " ")
(defonce ^:private rule (str divider divider divider))
(defonce ^:private column-start " | ") ;; We trim the leading space when rendering a row

(def ^:private ol-re #"^(\d+)\. ")

(defn- escape-ol
  "Takes a string s and escapes a dot if neccessary."
  [s]
  ;; using fn form here for compat between clj & cljs
  (s/replace s
             ol-re
             (fn [m]
               (str (second m) "\\. "))))

(defn- paragraph
  [value]
  (str (escape-ol value) linebreak))

(defn- header
  [depth value]
  (let [hashes (join (repeat depth "#"))]
    (str hashes whitespace value linebreak)))

(defn- blockquote
  [value]
  (str "> " value))

(defn- strikethrough
  [value]
  (str "~~" value "~~"))

(defn- strong
  [value]
  (str "**" value "**"))

(defn- emphasis
  [value]
  (str "*" value "*"))

(defn- list-
  [depth list-fn v]
  (if (vector? v)
    (case (first v)
      :ol (ordered-list (second v) (inc depth))
      :ul (unordered-list (second v) (inc depth))
      (list-fn (join (repeat (* depth 2) whitespace)) (pair->markdown v)))
    (let [padding (join (repeat (* depth 2) whitespace))]
      (list-fn padding v))))

(defn- list-entry
  [l]
  (if (map? l)
    (str (if (:done? l)
           "[x]"
           "[ ]")
         whitespace
         (:task l))
    l))

(defn- ordered-list
  ([col]
   (ordered-list col 0))
  ([col depth]
   (let [position (atom 1)
         render-fn #(str %1 @position ". " (list-entry %2) linebreak)
         position-fn #(do (swap! position inc) %)
         list-fn (partial list- depth (comp position-fn render-fn))]
     (->> col
          (map list-fn)
          (join)))))

(defn- unordered-list
  ([col]
   (unordered-list col 0))
  ([col depth]
   (->> col
        (map
         (partial list- depth #(str %1 "+ " (list-entry %2) linebreak)))
        (join))))

(defn- link
  [{:keys [text url title]}]
  (let [pad-title (if (nil? title) "" (str " \"" title "\""))]
    (str "[" text "](" url pad-title ")")))

(defn- anchor
  [value]
  (str "<a name=\"" value "\"></a>"))

(defn- code-block
  ([code]
   (code-block "" code))
  ([syntax code]
   (str "```" syntax linebreak code linebreak "```")))

(defn- code
  [value]
  (if (string? value)
    (code-block value)
    (let [values (first value)
          syntax (name (first values))
          code (second values)]
      (code-block syntax code))))

(defn- pad
  [padding value]
  (->> (str value padding)
       (take (count padding))
       (join)))

(defn- end-row
  [s]
  (str s column-end linebreak))

(defn- col
  [padding value]
  (->> (pad padding value)
       (str column-start)))

(defn- parse-cell-pair
  [[c cn]]
  (if (keyword? c)
    (pair->markdown [c cn])
    (if cn
      [c cn]
      c)))

(defn- parse-cells
  [cells]
  (->> (balance-when (comp odd? count) nil cells) ;; when cells collection in unbalanced pad with nil
       (partition 2)
       (map parse-cell-pair)
       (flatten)))

(defn- column
  [[column cells]]
  (let [parsed-cells (parse-cells cells)
        col-length (count column)
        max-data-length (longest parsed-cells)
        max-length (max col-length max-data-length)
        divider (join (repeat max-length divider))
        padding (join (repeat max-length whitespace))]
    {:header (col padding column)
     :divider (col padding divider)
     :cells (map (partial col padding) parsed-cells)}))

(defn- row
  [cells]
  (->> (join cells)
       (end-row)
       (triml)))

(defn- table
  [value]
  (let [cols-and-rows (partition 2 value)
        columns (map column cols-and-rows)
        cells (apply interleave (map :cells columns))
        cells-by-row (partition (count columns) cells)
        rows (flatten (map row cells-by-row))]
    (str
     (row (map :header columns))
     (row (map :divider columns))
     (join rows))))

(defn- pair->markdown
  [[node value]]
  (case node
    :br (if (= value :br) (str linebreak linebreak) linebreak)
    :hr (if (= value :hr) (str rule linebreak rule) rule)
    :p  (paragraph value)
    :h1 (header 1 value)
    :h2 (header 2 value)
    :h3 (header 3 value)
    :h4 (header 4 value)
    :h5 (header 5 value)
    :h6 (header 6 value)
    :blockquote (blockquote value)
    :strikethrough (strikethrough value)
    :i (emphasis value)
    :normal value
    :em (emphasis value)
    :strong (strong value)
    :b (strong value)
    :ol (ordered-list value)
    :ul (unordered-list value)
    :link (link value)
    :anchor (anchor value)
    :code (code value)
    :table (table value)))

(defn markdown
  "Takes a sequence of nodes and produces markdown."
  {:added "0.1.0"}
  [col]
  (->> col
       (balance-at #{:br :hr})
       (partition 2)
       (map (fn [pair] 
              (if (and (sequential? (last pair))
                       (keyword? (first (last pair))))
                (str 
                  (pair->markdown 
                    [(first pair) 
                     (case (first pair)
                       (:ul :ol) (map markdown (partition 2 (balance-at #{:br :hr} (last pair))))
                       (markdown (last pair)))]))
                (pair->markdown pair))))
       (join)))

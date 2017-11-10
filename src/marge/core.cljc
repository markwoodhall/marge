(ns marge.core
  "The core namespace contains all of the functions for rendering clojure data structures as
  markdown.

  The API documentation is available [here](http://markwoodhall.github.io/marge/).

  You can also view [blog posts] (http://markw.xyz/tags/marge/) about marge
  "
  {:author "Mark Woodhall"}
  (:require [clojure.string :refer [triml]]
            [marge.util :refer [balance-at balance-when longest]]))

(declare pair->markdown list- ordered-list unordered-list)

(def linebreak "\n")
(def column-end " |")
(def divider "-")
(def whitespace " ")
(def rule (str divider divider divider))
(def column-start " | ") ;; We trim the leading space when rendering a row

(defn- header
  [depth value]
  (let [hashes (apply str (repeat depth "#"))]
    (str hashes whitespace value linebreak)))

(defn- blockquote
  [value]
  (str "> " value))

(defn- list- 
  [depth list-fn v]
  (if (vector? v)
    (if (= :ol (first v))
      (ordered-list (second v) (inc depth))
      (unordered-list (second v) (inc depth)))
    (let [padding (apply str (repeat (* depth 2) whitespace))]
      (list-fn padding v))))

(defn- ordered-list
  ([col]
   (ordered-list col 0))
  ([col depth]
   (let [position (atom 1)
         render-fn #(str %1 @position ". " %2 linebreak)
         position-fn #(do (swap! position inc) %)
         list-fn (partial list- depth (comp position-fn render-fn))]
     (->> col
          (map list-fn)
          (apply str)))))

(defn- unordered-list
  ([col]
   (unordered-list col 0))
  ([col depth]
   (->> col
        (map 
          (partial list- depth #(str %1 "+ " %2 linebreak)))
        (apply str))))

(defn- link
  [{:keys [text url title]}]
  (let [pad-title (if (nil? title) "" (str " \"" title "\""))]
    (str "[" text "](" url pad-title")")))

(defn- code
  [value]
  (if (string? value)
    (str "```" linebreak value linebreak "```")
    (let [values (first value)
          syntax (name (first values))
          code (second values)]
      (str "```" syntax linebreak code linebreak "```"))))

(defn- pad
  [padding value]
  (->> (str value padding)
       (take (count padding))
       (apply str)))

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
  (->> (balance-when (comp odd? count) nil cells)
       (partition 2)
       (map parse-cell-pair)
       (flatten)))

(defn- column
  [[column cells]]
  (let [parsed-cells (parse-cells cells)
        col-length (count column)
        max-data-length (longest parsed-cells)
        max-length (max col-length max-data-length)
        divider (apply str (repeat max-length divider))
        padding (apply str (repeat max-length whitespace))]
    {:header (col padding column) 
     :divider (col padding divider) 
     :cells (map (partial col padding) parsed-cells)}))

(defn- row
  [cells]
  (->> (apply str cells)
       (str)
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
      (apply 
        str 
        rows))))

(defn- pair->markdown
  [[node value]]
  (case node
    :br (if (= value :br) (str linebreak linebreak) linebreak)
    :hr (if (= value :hr) (str rule linebreak rule) rule)
    :p (str value linebreak)
    :h1 (header 1 value)
    :h2 (header 2 value)
    :h3 (header 3 value)
    :h4 (header 4 value)
    :h5 (header 5 value)
    :h6 (header 6 value)
    :blockquote (blockquote value)
    :ol (ordered-list value)
    :ul (unordered-list value)
    :link (link value)
    :code (code value)
    :table (table value)))

(defn markdown
  "Takes a sequence of nodes and produces markdown."
  {:added "0.1.0"}
  [col]
  (apply str (map pair->markdown (partition 2 (balance-at #{:br :hr} col)))))

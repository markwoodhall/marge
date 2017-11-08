(ns marge.core
  "The core namespace contains all of the functions for rendering clojure data structures as
  markdown.

  The API documentation is available [here](http://markwoodhall.github.io/marge/).

  You can also view [blog posts] (http://markw.xyz/tags/marge/) about marge
  "
  {:author "Mark Woodhall"}
  (:require [clojure.string :refer [triml]]))

(declare pair->markdown ordered-list unordered-list)

(defn- header
  [depth value]
  (let [hashes (reduce str (repeat depth "#"))]
    (str hashes " " value "\n")))

(defn- blockquote
  [value]
  (str "> " value))

(defn- ordered-list
  ([col]
   (ordered-list col 0))
  ([col depth]
   (let [position (atom 0)]
     (->> col
          (map
            (fn map-ordered-list [v]
              (if (vector? v)
                (if (= :ol (first v))
                  (ordered-list (second v) (inc depth))
                  (unordered-list (second v) (inc depth)))
                (let [padding (apply str (repeat (* depth 2) " "))]
                  (swap! position inc)
                  (str padding @position ". " v "\n")))))
          (reduce str)))))

(defn- unordered-list
  ([col]
   (unordered-list col 0))
  ([col depth]
   (->> col
        (map 
          (fn map-unordered-list [v]
            (if (vector? v)
              (if (= :ol (first v))
                (ordered-list (second v) (inc depth))
                (unordered-list (second v) (inc depth)))
              (let [padding (apply str (repeat (* depth 2) " "))]
                (str padding "+ " v "\n")))))
        (reduce str))))

(defn- link
  [{:keys [text url title]}]
  (let [pad-title (if (nil? title) "" (str " \"" title "\""))]
    (str "[" text "](" url pad-title")")))

(defn- code
  [value]
  (if (string? value)
    (str "```\n" value "\n```")
    (let [values (first value)
          syntax (name (first values))
          code (second values)]
      (str "```" syntax "\n" code "\n```"))))

(defn- pad
  [padding value]
  (str " | " (reduce str (take (count padding) (str value padding)))))

(defn parse-rows
  [rows]
  (let [uniform-rows (if (odd? (count rows))
                       (conj rows nil)
                       rows)]
    (flatten 
      (map (fn map-rows 
             [[c cn]]
             (if (keyword? c)
               (pair->markdown [c cn])
               (if cn
                 [c cn]
                 c))) (partition 2 uniform-rows)))))

(defn- column
  [[col rows]]
  (let [parsed-rows (parse-rows rows)
        col-length (count col)
        max-data-length (if (empty? parsed-rows) 
                          0 
                          (apply max (map (comp count str) parsed-rows)))
        max-length (max col-length max-data-length)
        padding (reduce str (repeat max-length " "))]
    {:header (pad padding col)
     :divider (pad padding (reduce str (repeat max-length "-")))
     :cells (map (partial pad padding) parsed-rows)}))

(defn- table
  [value]
  (let [cols-and-rows (partition 2 value)
        columns (map column cols-and-rows)
        cells (apply interleave (map :cells columns))
        cells-by-row (partition (count columns) cells)
        rows (flatten (map #(triml ( str (reduce str %) " |\n")) cells-by-row))]
    (str 
      (triml (reduce str (map :header columns))) " |\n"
      (triml (reduce str (map :divider columns))) " |\n"
      (reduce 
        str 
        rows))))

(defn- pair->markdown
  [[node value]]
  (case node
    :br (if (= value :br) "\n\n" "\n")
    :hr (if (= value :hr) "---\n---" "---")
    :p (str value "\n")
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

(defn- balance-singulars
  [col]
  (mapcat
    identity 
    (map #(if (= (count %) 1)
            [(first %) nil]
            %) (partition-by #{:br :hr} col))))

(defn markdown
  "Takes a sequence of nodes and produces markdown."
  {:added "0.1.0"}
  [col]
  (reduce str (map pair->markdown (partition 2 (balance-singulars col)))))

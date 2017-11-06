(ns marge.core
  "The core namespace contains all of the functions for rendering clojure data structures as
  markdown.

  The API documentation is available [here](http://markwoodhall.github.io/marge/).

  You can also view [blog posts] (http://markw.xyz/tags/marge/) about marge
  "
  {:author "Mark Woodhall"}
  (:require [clojure.string :refer [triml]]))

(defn- header
  [depth value]
  (let [hashes (reduce str (repeat depth "#"))]
    (str hashes " " value "\n")))

(defn- blockquote
  [value]
  (str "> " value))

(defn- ordered-list
  [col]
  (->> col
       (map-indexed #(str (inc %1) ". " %2 "\n"))
       (reduce str)))

(defn- unordered-list
  [col]
  (->> col
       (map #(str "+ " % "\n"))
       (reduce str)))

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

(defn- column
  [[col rows]]
  (let [col-length (count col)
        max-data-length (apply max (map (comp count str) rows))
        max-length (max col-length max-data-length)
        padding (reduce str (repeat max-length " "))]
    {:header (pad padding col)
     :divider (pad padding (reduce str (repeat max-length "-")))
     :cells (map (partial pad padding) rows)}))

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

(defn- balance-line-breaks
  [col]
  (mapcat
    identity 
    (map #(if (= (count %) 1)
            [(first %) nil]
            %) (partition-by #{:br} col))))

(defn markdown
  "Takes a sequence of nodes and produces markdown."
  {:added "0.1.0"}
  [col]
  (reduce str (map pair->markdown (partition 2 (balance-line-breaks col)))))

(ns marge.core
  "The core namespace contains all of the functions for rendering clojure data structures as
  markdown.

  The API documentation is available [here](http://markwoodhall.github.io/marge/).

  You can also view [blog posts] (http://markw.xyz/tags/marge/) about marge
  "
  {:author "Mark Woodhall"})

(defn- header
  [depth value]
  (let [hashes (reduce str (repeat depth "#"))]
    (str hashes " " value)))

(defn- blockquote
  [value]
  (str "> " value))

(defn- ordered-list
  [col]
  (->> col
       (map-indexed #(str (inc %1) ". " %2 "\n"))
       (reduce str)))

(defn- pair->markdown
  [[node value]]
  (case node
    :# (header 1 value)
    :h1 (header 1 value)
    :## (header 2 value)
    :h2 (header 2 value)
    :### (header 3 value)
    :h3 (header 3 value)
    :#### (header 4 value)
    :h4 (header 4 value)
    :##### (header 5 value)
    :h5 (header 5 value)
    :###### (header 6 value)
    :h6 (header 6 value)
    :blockquote (blockquote value)
    :> (blockquote value)
    :ol (ordered-list value)))

(defn markdown
  "Takes a sequence of nodes and produces markdown."
  {:added "0.1.0"}
  [col]
  (reduce str (map pair->markdown (partition 2 col))))

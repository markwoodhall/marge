(ns marge.core
  "The core namespace contains all of the functions for rendering clojure data structures as
  markdown.

  The API documentation is available [here](http://markwoodhall.github.io/marge/).

  You can also view [blog posts] (http://markw.xyz/tags/marge/) about marge
  "
  {:author "Mark Woodhall"})

;; headers

(defn- pair->markdown
  [[node value]]
  (case node
    :h1 (str "# " value)
    :h2 (str "## " value)
    :h3 (str "### " value)
    :h4 (str "#### " value)
    :h5 (str "##### " value)
    :h6 (str "###### " value)))

(defn markdown
  "Takes a sequence of nodes and produces markdown."
  {:added "0.1.0"}
  [col]
  (reduce str (map pair->markdown(partition 2 col))))

(ns user
  (:require
   [clojure.tools.namespace.repl :refer :all]))

(when (System/getProperty "marge.load_nrepl")
  (require 'nrepl))

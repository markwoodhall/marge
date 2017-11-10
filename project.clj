(defproject marge "0.9.0"
  :description "A library to generate markdown using Clojure data structures, hiccup for markdown."
  :url "http://github.com/markwoodhall/marge"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :codox {:metadata {:doc/format :markdown}
          :namespaces [marge.core]
          :source-uri "https://github.com/markwoodhall/marge/blob/master/src/{classpath}#L{line}"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]]
  :jar-exclusions [#"\.swp|\.swo|user.clj"]
  :source-paths ["src"])

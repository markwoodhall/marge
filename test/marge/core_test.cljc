(ns marge.core-test
  (:require #?(:cljs [cljs.test :as t])
            #?(:clj  [clojure.test :as t])
            [marge.core :refer [markdown]]))

(t/deftest headers
  (t/testing "h1 headers produces expected string"
    (t/is (= "# Refactoring"
             (markdown [:h1 "Refactoring"])))
    (t/is (= "# Refactoring"
             (markdown [:# "Refactoring"]))))

  (t/testing "h2 headers produces expected string"
    (t/is (= "## Testing"
             (markdown [:h2 "Testing"])))
    (t/is (= "## Testing"
             (markdown [:## "Testing"]))))

  (t/testing "h3 headers produces expected string"
    (t/is (= "### Documenting"
             (markdown [:h3 "Documenting"])))
    (t/is (= "### Documenting"
             (markdown [:### "Documenting"]))))
  
  (t/testing "h4 headers produces expected string"
    (t/is (= "#### Supporting"
             (markdown [:h4 "Supporting"])))
    (t/is (= "#### Supporting"
             (markdown [:#### "Supporting"]))))
  
  (t/testing "h5 headers produces expected string"
    (t/is (= "##### Bug Fixing"
             (markdown [:h5 "Bug Fixing"])))
    (t/is (= "##### Bug Fixing"
             (markdown [:##### "Bug Fixing"]))))
  
  (t/testing "h6 headers produces expected string"
    (t/is (= "###### Features"
             (markdown [:h6 "Features"])))
    (t/is (= "###### Features"
             (markdown [:###### "Features"])))))

(t/deftest quotes
  (t/testing "blockquote produces expected string"
    (t/is (= "> Blockquotes are very handy"
             (markdown [:blockquote "Blockquotes are very handy"])))))

#?(:cljs
    (do
      (enable-console-print!)
      (set! *main-cli-fn* #(t/run-tests))))

#?(:cljs
    (defmethod t/report [:cljs.test/default :end-run-tests]
      [m]
      (if (t/successful? m)
        (set! (.-exitCode js/process) 0)
        (set! (.-exitCode js/process) 1))))

(ns marge.core-test
  (:require #?(:cljs [cljs.test :as t])
            #?(:clj  [clojure.test :as t])
            [marge.core :refer [markdown]]))

(t/deftest headers
  (t/testing "h1 headers produces expected string"
    (t/is (= "# Refactoring"
             (markdown [:h1 "Refactoring"]))))

  (t/testing "h2 headers produces expected string"
    (t/is (= "## Testing"
             (markdown [:h2 "Testing"]))))

  (t/testing "h3 headers produces expected string"
    (t/is (= "### Documenting"
             (markdown [:h3 "Documenting"]))))
  
  (t/testing "h4 headers produces expected string"
    (t/is (= "#### Supporting"
             (markdown [:h4 "Supporting"]))))
  
  (t/testing "h5 headers produces expected string"
    (t/is (= "##### Bug Fixing"
             (markdown [:h5 "Bug Fixing"]))))
  
  (t/testing "h6 headers produces expected string"
    (t/is (= "###### Features"
             (markdown [:h6 "Features"])))))

(t/deftest quotes
  (t/testing "blockquote produces expected string"
    (t/is (= "> Blockquotes are very handy"
             (markdown [:blockquote "Blockquotes are very handy"])))))

(t/deftest lists
  (t/testing "ordered list produces expected string"
    (t/is (= "1. First item\n2. Second item\n"
             (markdown [:ol ["First item" "Second item"]]))))
  
  (t/testing "unordered list produces expected string"
    (t/is (= "+ First item\n+ Second item\n"
             (markdown [:ul ["First item" "Second item"]])))))

(t/deftest links
  (t/testing "inline link returns expected string"
    (t/is (= "[I'm an inline-style link](https://www.google.com)"
             (markdown [:link {:text "I'm an inline-style link" :url "https://www.google.com"}]))))
  
  (t/testing "inline link with title returns expected string"
    (t/is (= "[I'm an inline-style link](https://www.google.com \"Google Homepage\")"
             (markdown [:link 
                        {:text "I'm an inline-style link" 
                         :url "https://www.google.com" 
                         :title "Google Homepage"}])))))

(t/deftest composing-nodes
  (t/testing "multiple nodes return the expected string"
    (t/is (= "# Header\n\n1. First item\n2. Second item\n\n## Header 2"
             (markdown [:h1 "Header"
                        :br :br
                        :ol ["First item" "Second item"]
                        :br
                        :h2 "Header 2"])))))

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

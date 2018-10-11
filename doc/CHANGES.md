# Change Log

### 0.15.0 (11-10-2018)

* Better support for node composition.

    ```clojure
    (markdown [:p 
               [:normal "When I want to search for things I go to "
                :em [:link {:url "http://google.com" :text "google"}]]])
    ```

* Support for avoiding unintended OL by escaping in paragraphs [link](https://github.com/markwoodhall/marge/pull/2).

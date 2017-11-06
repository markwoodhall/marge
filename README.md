# marge
Markdown generation for Clojure and ClojureScript.

## Status

[![Build Status](https://api.travis-ci.org/markwoodhall/marge.svg?branch=master)](https://api.travis-ci.org/repositories/markwoodhall/marge)
[![Clojars Project](https://img.shields.io/clojars/v/marge.svg)](http://clojars.org/marge)

## Usage


```clojure
(require '[marge.core :as marge])
```

### Headings

```clojure
(marge/markdown 
 [:h1 "My Heading"])
```

### Quotes

```clojure
(marge/markdown
 [:blockquote "Something quote worthy!"])
```

### Lists

```clojure
(marge/markdown
 [:ol ["Item 1" "Item 2"]])

(marge/markdown
 [:ul ["Item 1" "Item 2"]])
```

### Links

```clojure
(marge/markdown 
 [:link 
  {:text "I'm an inline-style link" 
   :title "Google Homepage"
   :url "https://www.google.com"}])
```

### Code

```clojure
(marge/markdown 
 [:code
  {:clojure "(def data [1 2 3])"}])
```

### Tables

```clojure
(markdown 
[:table
 ["Product" 
  ["Coke" "Fanta" "Lilt"] 
  "Quantity" 
  ["100" "10000000" "1"]
  "Price"
  ["$70" "$7000000" "$2"]]])
```

### Composition

```clojure
(markdown 
 [:h1 "Header"
  :ol ["First item" "Second item"]
  :h2 "Header 2"])
```

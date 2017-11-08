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
 [:h4 "My Heading"])
```

#### My Heading

### Quotes

```clojure
(marge/markdown
 [:blockquote "Something quote worthy!"])
```

> Something quote worthy!

### Lists

```clojure
(marge/markdown
 [:ol ["Item 1" "Item 2"]])
```

1. Item 1
2. Item 2

```clojure
(marge/markdown
 [:ul ["Item 1" "Item 2"]])
```

+ Item 1
+ Item 2

```clojure
(marge/markdown
 [:ol ["Item 1" 
       [:ul "Sub Item 1" "Sub Item 2"]
       "Item 2"]])
```

1. Item 1
  + Sub Item 1
  + Sub Item 2
2. Item 2

```clojure
(marge/markdown
 [:ol ["Item 1" 
       [:ol "Sub Item 1" "Sub Item 2"]
       "Item 2"]])
```

1. Item 1
  1. Sub Item 1
  2. Sub Item 2
2. Item 2

### Links

```clojure
(marge/markdown 
 [:link 
  {:text "I'm an inline-style link" 
   :title "Google Homepage"
   :url "https://www.google.com"}])
```

### Horizontal Rule

```clojure
(marge/markdown 
 [:hr])
```

---

[I'm an inline-style link](https://www.google.com "Google Homepage")

### Code

```clojure
(marge/markdown 
 [:code
  {:clojure "(def data [1 2 3])"}])
```

```clojure
(def data [1 2 3])
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

| Product | Quantity | Price    |
| ------- | -------- | -------- |
| Coke    | 100      | $70      |
| Fanta   | 10000000 | $7000000 |
| Lilt    | 1        | $2       |


#### Markdown in a table

```clojure
(markdown 
  [:table
   ["Title" 
    ["link"]
    "Links" 
    [:link {:url "url" :text "text"}]]])

| Title | Links       |
| ----- | ----------- |
| link  | [text](url) |

### Composition

```clojure
(markdown 
 [:h1 "Header"
  :ol ["First item" "Second item"]
  :h2 "Header 2"])
```

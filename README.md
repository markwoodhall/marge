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

### Normal

```clojure
(marge/markdown
 [:normal "Done this!"])
```

Done this!

### Strikethrough

```clojure
(marge/markdown
 [:strikethrough "Done this!"])
```

~~Done this!~~


### Strong

```clojure
(marge/markdown
 [:strong "Done this!"])
```

**Done this!**

### Emphasis

```clojure
(marge/markdown
 [:em "Done this!"])
```

*Done this!*

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
 [:ul [{:done? true :task "Item 1"} {:done? false :task "Item 2"}]])
```

+ [x] Item 1
+ [ ] Item 2

### Links

```clojure
(marge/markdown 
 [:link 
  {:text "I'm an inline-style link" 
   :title "Google Homepage"
   :url "https://www.google.com"}])
```

[I'm an inline-style link](https://www.google.com "Google Homepage")


### Anchors

Sometimes you might want to place an anchor that you link to later.

```clojure
(marge/markdown 
 [:anchor "Anchor"])
```

<a name="Anchor"></a>


[Test Anchor Link](#Anchor)

### Horizontal Rule

```clojure
(marge/markdown 
 [:hr])
```

---

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
```

| Title | Links       |
| ----- | ----------- |
| link  | [text](url) |

### HTML Inside Markdown

You can generate html inside your markdown using Hiccup syntax, the rendering for this uses Hiccup for Clojure or Hiccups for ClojureScript.

```clojure
(markdown [:p 
           [:normal "When I want to search for things I go to "
            :html [:a {:href "http://google.com"} "google"]]])
```

When I want to search for things I go to <a href="http://google.com">google</a>

### Composition

```clojure
(markdown [:p 
           [:normal "When I want to search for things I go to "
            :em [:link {:url "http://google.com" :text "google"}]]])
```

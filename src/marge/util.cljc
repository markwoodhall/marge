(ns marge.util)

(defn balance-when
  [f balancer col]
  (if (f col)
    (conj col balancer)
    col))

(defn balance-at
  [f col]
  (mapcat
    identity 
    (map #(if (= (count %) 1)
            [(first %) nil]
            %) (partition-by f col))))

(defn longest
  [col]
  (if (empty? col) 
    0 
    (apply max (map (comp count str) col))))

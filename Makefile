.PHONY: test test-clj test-cljs

test: test test-cljs

test-clj:
	clojure -A:test-clj

test-cljs:
	clojure -A:test-cljs

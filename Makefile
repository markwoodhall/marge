.PHONY: test test-clj test-cljs

test: test-clj test-cljs

test-clj:
	clojure -A:test

test-cljs:
	clojure -A:test-cljs

pom:
	clj -Spom

deploy: test
	clj -Spom
	mvn deploy

.PHONY: test test-clj test-cljs pom

test: test-clj test-cljs

test-clj:
	clojure -A:test

test-cljs:
	clojure -A:test-cljs

pom:
	clojure -Spom

deploy: test
	clojure -Spom
	mvn deploy

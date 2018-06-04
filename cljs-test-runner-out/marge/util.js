// Compiled by ClojureScript 1.9.946 {:target :nodejs}
goog.provide('marge.util');
goog.require('cljs.core');
marge.util.balance_when = (function marge$util$balance_when(f,balancer,col){
if(cljs.core.truth_(f.call(null,col))){
return cljs.core.conj.call(null,col,balancer);
} else {
return col;
}
});
marge.util.balance_at = (function marge$util$balance_at(f,col){
return cljs.core.mapcat.call(null,cljs.core.identity,cljs.core.map.call(null,(function (p1__10661_SHARP_){
if(cljs.core._EQ_.call(null,cljs.core.count.call(null,p1__10661_SHARP_),(1))){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.first.call(null,p1__10661_SHARP_),null], null);
} else {
return p1__10661_SHARP_;
}
}),cljs.core.partition_by.call(null,f,col)));
});
marge.util.longest = (function marge$util$longest(col){
if(cljs.core.empty_QMARK_.call(null,col)){
return (0);
} else {
return cljs.core.apply.call(null,cljs.core.max,cljs.core.map.call(null,cljs.core.comp.call(null,cljs.core.count,cljs.core.str),col));
}
});

//# sourceMappingURL=util.js.map

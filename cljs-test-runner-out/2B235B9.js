goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__11139__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__11139 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__11140__i = 0, G__11140__a = new Array(arguments.length -  0);
while (G__11140__i < G__11140__a.length) {G__11140__a[G__11140__i] = arguments[G__11140__i + 0]; ++G__11140__i;}
  args = new cljs.core.IndexedSeq(G__11140__a,0,null);
} 
return G__11139__delegate.call(this,args);};
G__11139.cljs$lang$maxFixedArity = 0;
G__11139.cljs$lang$applyTo = (function (arglist__11141){
var args = cljs.core.seq(arglist__11141);
return G__11139__delegate(args);
});
G__11139.cljs$core$IFn$_invoke$arity$variadic = G__11139__delegate;
return G__11139;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__11142__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__11142 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__11143__i = 0, G__11143__a = new Array(arguments.length -  0);
while (G__11143__i < G__11143__a.length) {G__11143__a[G__11143__i] = arguments[G__11143__i + 0]; ++G__11143__i;}
  args = new cljs.core.IndexedSeq(G__11143__a,0,null);
} 
return G__11142__delegate.call(this,args);};
G__11142.cljs$lang$maxFixedArity = 0;
G__11142.cljs$lang$applyTo = (function (arglist__11144){
var args = cljs.core.seq(arglist__11144);
return G__11142__delegate(args);
});
G__11142.cljs$core$IFn$_invoke$arity$variadic = G__11142__delegate;
return G__11142;
})()
;

return null;
});

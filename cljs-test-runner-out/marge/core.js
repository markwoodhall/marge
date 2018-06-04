// Compiled by ClojureScript 1.9.946 {:target :nodejs}
goog.provide('marge.core');
goog.require('cljs.core');
goog.require('clojure.string');
goog.require('marge.util');



if(typeof marge.core.linebreak !== 'undefined'){
} else {
marge.core.linebreak = "\n";
}
if(typeof marge.core.column_end !== 'undefined'){
} else {
marge.core.column_end = " |";
}
if(typeof marge.core.divider !== 'undefined'){
} else {
marge.core.divider = "-";
}
if(typeof marge.core.whitespace !== 'undefined'){
} else {
marge.core.whitespace = " ";
}
if(typeof marge.core.rule !== 'undefined'){
} else {
marge.core.rule = [cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.divider),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.divider),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.divider)].join('');
}
if(typeof marge.core.column_start !== 'undefined'){
} else {
marge.core.column_start = " | ";
}
marge.core.header = (function marge$core$header(depth,value){
var hashes = clojure.string.join.call(null,cljs.core.repeat.call(null,depth,"#"));
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(hashes),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.whitespace),cljs.core.str.cljs$core$IFn$_invoke$arity$1(value),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak)].join('');
});
marge.core.blockquote = (function marge$core$blockquote(value){
return ["> ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(value)].join('');
});
marge.core.strikethrough = (function marge$core$strikethrough(value){
return ["~~",cljs.core.str.cljs$core$IFn$_invoke$arity$1(value),"~~"].join('');
});
marge.core.list_ = (function marge$core$list_(depth,list_fn,v){
if(cljs.core.vector_QMARK_.call(null,v)){
var G__10661 = cljs.core.first.call(null,v);
var G__10661__$1 = (((G__10661 instanceof cljs.core.Keyword))?G__10661.fqn:null);
switch (G__10661__$1) {
case "ol":
return marge.core.ordered_list.call(null,cljs.core.second.call(null,v),(depth + (1)));

break;
case "ul":
return marge.core.unordered_list.call(null,cljs.core.second.call(null,v),(depth + (1)));

break;
default:
return list_fn.call(null,clojure.string.join.call(null,cljs.core.repeat.call(null,(depth * (2)),marge.core.whitespace)),marge.core.pair__GT_markdown.call(null,v));

}
} else {
var padding = clojure.string.join.call(null,cljs.core.repeat.call(null,(depth * (2)),marge.core.whitespace));
return list_fn.call(null,padding,v);
}
});
marge.core.list_entry = (function marge$core$list_entry(l){
if(cljs.core.map_QMARK_.call(null,l)){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1((cljs.core.truth_(new cljs.core.Keyword(null,"done?","done?",-1847001718).cljs$core$IFn$_invoke$arity$1(l))?"[x]":"[ ]")),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.whitespace),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"task","task",-1476607993).cljs$core$IFn$_invoke$arity$1(l))].join('');
} else {
return l;
}
});
marge.core.ordered_list = (function marge$core$ordered_list(var_args){
var G__10667 = arguments.length;
switch (G__10667) {
case 1:
return marge.core.ordered_list.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return marge.core.ordered_list.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

marge.core.ordered_list.cljs$core$IFn$_invoke$arity$1 = (function (col){
return marge.core.ordered_list.call(null,col,(0));
});

marge.core.ordered_list.cljs$core$IFn$_invoke$arity$2 = (function (col,depth){
var position = cljs.core.atom.call(null,(1));
var render_fn = ((function (position){
return (function (p1__10663_SHARP_,p2__10664_SHARP_){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(p1__10663_SHARP_),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,position)),". ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.list_entry.call(null,p2__10664_SHARP_)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak)].join('');
});})(position))
;
var position_fn = ((function (position,render_fn){
return (function (p1__10665_SHARP_){
cljs.core.swap_BANG_.call(null,position,cljs.core.inc);

return p1__10665_SHARP_;
});})(position,render_fn))
;
var list_fn = cljs.core.partial.call(null,marge.core.list_,depth,cljs.core.comp.call(null,position_fn,render_fn));
return clojure.string.join.call(null,cljs.core.map.call(null,list_fn,col));
});

marge.core.ordered_list.cljs$lang$maxFixedArity = 2;

marge.core.unordered_list = (function marge$core$unordered_list(var_args){
var G__10672 = arguments.length;
switch (G__10672) {
case 1:
return marge.core.unordered_list.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return marge.core.unordered_list.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

marge.core.unordered_list.cljs$core$IFn$_invoke$arity$1 = (function (col){
return marge.core.unordered_list.call(null,col,(0));
});

marge.core.unordered_list.cljs$core$IFn$_invoke$arity$2 = (function (col,depth){
return clojure.string.join.call(null,cljs.core.map.call(null,cljs.core.partial.call(null,marge.core.list_,depth,(function (p1__10669_SHARP_,p2__10670_SHARP_){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(p1__10669_SHARP_),"+ ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.list_entry.call(null,p2__10670_SHARP_)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak)].join('');
})),col));
});

marge.core.unordered_list.cljs$lang$maxFixedArity = 2;

marge.core.link = (function marge$core$link(p__10674){
var map__10675 = p__10674;
var map__10675__$1 = ((((!((map__10675 == null)))?((((map__10675.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__10675.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__10675):map__10675);
var text = cljs.core.get.call(null,map__10675__$1,new cljs.core.Keyword(null,"text","text",-1790561697));
var url = cljs.core.get.call(null,map__10675__$1,new cljs.core.Keyword(null,"url","url",276297046));
var title = cljs.core.get.call(null,map__10675__$1,new cljs.core.Keyword(null,"title","title",636505583));
var pad_title = (((title == null))?"":[" \"",cljs.core.str.cljs$core$IFn$_invoke$arity$1(title),"\""].join(''));
return ["[",cljs.core.str.cljs$core$IFn$_invoke$arity$1(text),"](",cljs.core.str.cljs$core$IFn$_invoke$arity$1(url),cljs.core.str.cljs$core$IFn$_invoke$arity$1(pad_title),")"].join('');
});
marge.core.anchor = (function marge$core$anchor(value){
return ["<a name=\"",cljs.core.str.cljs$core$IFn$_invoke$arity$1(value),"\"></a>"].join('');
});
marge.core.code_block = (function marge$core$code_block(var_args){
var G__10678 = arguments.length;
switch (G__10678) {
case 1:
return marge.core.code_block.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return marge.core.code_block.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

marge.core.code_block.cljs$core$IFn$_invoke$arity$1 = (function (code){
return marge.core.code_block.call(null,"",code);
});

marge.core.code_block.cljs$core$IFn$_invoke$arity$2 = (function (syntax,code){
return ["```",cljs.core.str.cljs$core$IFn$_invoke$arity$1(syntax),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak),cljs.core.str.cljs$core$IFn$_invoke$arity$1(code),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak),"```"].join('');
});

marge.core.code_block.cljs$lang$maxFixedArity = 2;

marge.core.code = (function marge$core$code(value){
if(typeof value === 'string'){
return marge.core.code_block.call(null,value);
} else {
var values = cljs.core.first.call(null,value);
var syntax = cljs.core.name.call(null,cljs.core.first.call(null,values));
var code = cljs.core.second.call(null,values);
return marge.core.code_block.call(null,syntax,code);
}
});
marge.core.pad = (function marge$core$pad(padding,value){
return clojure.string.join.call(null,cljs.core.take.call(null,cljs.core.count.call(null,padding),[cljs.core.str.cljs$core$IFn$_invoke$arity$1(value),cljs.core.str.cljs$core$IFn$_invoke$arity$1(padding)].join('')));
});
marge.core.end_row = (function marge$core$end_row(s){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(s),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.column_end),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak)].join('');
});
marge.core.col = (function marge$core$col(padding,value){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.column_start),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.pad.call(null,padding,value))].join('');
});
marge.core.parse_cell_pair = (function marge$core$parse_cell_pair(p__10680){
var vec__10681 = p__10680;
var c = cljs.core.nth.call(null,vec__10681,(0),null);
var cn = cljs.core.nth.call(null,vec__10681,(1),null);
if((c instanceof cljs.core.Keyword)){
return marge.core.pair__GT_markdown.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [c,cn], null));
} else {
if(cljs.core.truth_(cn)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [c,cn], null);
} else {
return c;
}
}
});
marge.core.parse_cells = (function marge$core$parse_cells(cells){
return cljs.core.flatten.call(null,cljs.core.map.call(null,marge.core.parse_cell_pair,cljs.core.partition.call(null,(2),marge.util.balance_when.call(null,cljs.core.comp.call(null,cljs.core.odd_QMARK_,cljs.core.count),null,cells))));
});
marge.core.column = (function marge$core$column(p__10684){
var vec__10685 = p__10684;
var column = cljs.core.nth.call(null,vec__10685,(0),null);
var cells = cljs.core.nth.call(null,vec__10685,(1),null);
var parsed_cells = marge.core.parse_cells.call(null,cells);
var col_length = cljs.core.count.call(null,column);
var max_data_length = marge.util.longest.call(null,parsed_cells);
var max_length = (function (){var x__9288__auto__ = col_length;
var y__9289__auto__ = max_data_length;
return ((x__9288__auto__ > y__9289__auto__) ? x__9288__auto__ : y__9289__auto__);
})();
var divider = clojure.string.join.call(null,cljs.core.repeat.call(null,max_length,marge.core.divider));
var padding = clojure.string.join.call(null,cljs.core.repeat.call(null,max_length,marge.core.whitespace));
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"header","header",119441134),marge.core.col.call(null,padding,column),new cljs.core.Keyword(null,"divider","divider",1265972657),marge.core.col.call(null,padding,divider),new cljs.core.Keyword(null,"cells","cells",-985166822),cljs.core.map.call(null,cljs.core.partial.call(null,marge.core.col,padding),parsed_cells)], null);
});
marge.core.row = (function marge$core$row(cells){
return clojure.string.triml.call(null,marge.core.end_row.call(null,clojure.string.join.call(null,cells)));
});
marge.core.table = (function marge$core$table(value){
var cols_and_rows = cljs.core.partition.call(null,(2),value);
var columns = cljs.core.map.call(null,marge.core.column,cols_and_rows);
var cells = cljs.core.apply.call(null,cljs.core.interleave,cljs.core.map.call(null,new cljs.core.Keyword(null,"cells","cells",-985166822),columns));
var cells_by_row = cljs.core.partition.call(null,cljs.core.count.call(null,columns),cells);
var rows = cljs.core.flatten.call(null,cljs.core.map.call(null,marge.core.row,cells_by_row));
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.row.call(null,cljs.core.map.call(null,new cljs.core.Keyword(null,"header","header",119441134),columns))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.row.call(null,cljs.core.map.call(null,new cljs.core.Keyword(null,"divider","divider",1265972657),columns))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(clojure.string.join.call(null,rows))].join('');
});
marge.core.pair__GT_markdown = (function marge$core$pair__GT_markdown(p__10688){
var vec__10689 = p__10688;
var node = cljs.core.nth.call(null,vec__10689,(0),null);
var value = cljs.core.nth.call(null,vec__10689,(1),null);
var G__10692 = node;
var G__10692__$1 = (((G__10692 instanceof cljs.core.Keyword))?G__10692.fqn:null);
switch (G__10692__$1) {
case "br":
if(cljs.core._EQ_.call(null,value,new cljs.core.Keyword(null,"br","br",934104792))){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak)].join('');
} else {
return marge.core.linebreak;
}

break;
case "hr":
if(cljs.core._EQ_.call(null,value,new cljs.core.Keyword(null,"hr","hr",1377740067))){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.rule),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.rule)].join('');
} else {
return marge.core.rule;
}

break;
case "p":
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(value),cljs.core.str.cljs$core$IFn$_invoke$arity$1(marge.core.linebreak)].join('');

break;
case "h1":
return marge.core.header.call(null,(1),value);

break;
case "h2":
return marge.core.header.call(null,(2),value);

break;
case "h3":
return marge.core.header.call(null,(3),value);

break;
case "h4":
return marge.core.header.call(null,(4),value);

break;
case "h5":
return marge.core.header.call(null,(5),value);

break;
case "h6":
return marge.core.header.call(null,(6),value);

break;
case "blockquote":
return marge.core.blockquote.call(null,value);

break;
case "strikethrough":
return marge.core.strikethrough.call(null,value);

break;
case "ol":
return marge.core.ordered_list.call(null,value);

break;
case "ul":
return marge.core.unordered_list.call(null,value);

break;
case "link":
return marge.core.link.call(null,value);

break;
case "anchor":
return marge.core.anchor.call(null,value);

break;
case "code":
return marge.core.code.call(null,value);

break;
case "table":
return marge.core.table.call(null,value);

break;
default:
throw (new Error(["No matching clause: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(G__10692__$1)].join('')));

}
});
/**
 * Takes a sequence of nodes and produces markdown.
 */
marge.core.markdown = (function marge$core$markdown(col){
return clojure.string.join.call(null,cljs.core.map.call(null,marge.core.pair__GT_markdown,cljs.core.partition.call(null,(2),marge.util.balance_at.call(null,new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"hr","hr",1377740067),null,new cljs.core.Keyword(null,"br","br",934104792),null], null), null),col))));
});

//# sourceMappingURL=core.js.map

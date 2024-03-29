(function(l) {
	function s(a, b, d) {
		l.ajax({
			url : a,
			async : false,
			cache : false,
			contentType : "text/plain;charset=UTF-8",
			dataType : "text",
			success : function(e) {
				var j = "";
				e = e.split(/\n/);
				for (var m = /(\{\d+\})/g, r = /\{(\d+)\}/g, h = /(\\u.{4})/ig, k = 0; k < e.length; k++) {
					e[k] = e[k].replace(/^\s\s*/, "").replace(/\s\s*$/, "");
					if (e[k].length > 0 && e[k].match("^#") != "#") {
						var c = e[k].split("=");
						if (c.length > 0) {
							for (var p = unescape(c[0]).replace(/^\s\s*/, "").replace(/\s\s*$/, ""), f = c.length == 1 ? "" : c[1]; f.match(/\\$/) == "\\"; ) {
								f = f.substring(0, f.length - 1);
								f += e[++k].replace(/\s\s*$/, "")
							}
							for (var g = 2; g < c.length; g++)
								f += "=" + c[g];
							f = f.replace(/"/g, '\\"');
							f = f.replace(/^\s\s*/, "").replace(/\s\s*$/, "");
							if (d == "map" || d == "both") {
								if ( c = f.match(h))
									for ( g = 0; g < c.length; g++)
										f = f.replace(c[g], u(c[g]));
								l.i18n.map[p] = f
							}
							if (d == "vars" || d == "both") {
								g = p;
								if (/\./.test(g)) {
									c = "";
									g = g.split(/\./);
									for (var o = 0; o < g.length; o++) {
										if (o > 0)
											c += ".";
										c += g[o];
										eval("typeof " + c + ' == "undefined"') && eval(c + "={};")
									}
								}
								if (m.test(f)) {
									c = f.split(m);
									g = true;
									o = "";
									for (var t = [], q = 0; q < c.length; q++)
										if (m.test(c[q]) && (t.length == 0 || t.indexOf(c[q]) == -1)) {
											g || (o += ",");
											o += c[q].replace(r, "v$1");
											t.push(c[q]);
											g = false
										}
									j += p + "=function(" + o + "){";
									p = '"' + f.replace(r, '"+v$1+"') + '"';
									j += "return " + p + ";};"
								} else
									j += p + '="' + f + '";'
							}
						}
					}
				}
				eval(j)
			}
		})
	}

	function u(a) {
		var b = [];
		a = parseInt(a.substr(2), 16);
		a >= 0 && a < Math.pow(2, 16) && b.push(a);
		a = "";
		for (var d = 0; d < b.length; ++d)
			a += String.fromCharCode(b[d]);
		return a
	}
	l.i18n = {};
	l.i18n.map = {};
	l.i18n.properties = function(a) {
		a = l.extend({
			name : "Messages",
			language : "",
			path : "",
			mode : "vars",
			callback : function() {
			}
		}, a);
		if (a.language === null || a.language == "")
			a.language = l.i18n.browserLang();
		if (a.language === null)
			a.language = "";
		var b = a.name && a.name.constructor == Array ? a.name : [a.name];
		for ( i = 0; i < b.length; i++) {
			s(a.path + b[i] + ".properties", a.language, a.mode);
			a.language.length >= 2 && s(a.path + b[i] + "_" + a.language.substring(0, 2) + ".properties", a.language, a.mode);
			a.language.length >= 5 && s(a.path + b[i] + "_" + a.language.substring(0, 5) + ".properties", a.language, a.mode)
		}
		a.callback && a.callback()
	};
	l.i18n.prop = function(a, b) {
		var d = l.i18n.map[a];
		if (d == null)
			return "[" + a + "]";
		if (b)
			for (var e = 0; e < b.length; e++) {
				var j = RegExp("\\{(" + e + ")\\}", "g");
				d = d.replace(j, b[e])
			}
		return d
	};
	l.i18n.browserLang = function() {
		var a = navigator.language || navigator.userLanguage;
		a = a.toLowerCase();
		if (a.length > 3)
			a = a.substring(0, 3) + a.substring(3).toUpperCase();
		return a
	};
	var n;
	if (!n) {
		n = function(a, b, d) {
			if (Object.prototype.toString.call(b) !== "[object RegExp]")
				return typeof n._nativeSplit == "undefined" ? a.split(b, d) : n._nativeSplit.call(a, b, d);
			var e = [], j = 0, m = (b.ignoreCase ? "i" : "") + (b.multiline ? "m" : "") + (b.sticky ? "y" : "");
			b = RegExp(b.source, m + "g");
			var r, h, k;
			a += "";
			n._compliantExecNpcg || ( r = RegExp("^" + b.source + "$(?!\\s)", m));
			if (d === undefined || +d < 0)
				d = Infinity;
			else {
				d = Math.floor(+d);
				if (!d)
					return []
			}
			for (; h = b.exec(a); ) {
				m = h.index + h[0].length;
				if (m > j) {
					e.push(a.slice(j, h.index));
					!n._compliantExecNpcg && h.length > 1 && h[0].replace(r, function() {
						for (var c = 1; c < arguments.length - 2; c++)
							if (arguments[c] === undefined)
								h[c] = undefined
					});
					h.length > 1 && h.index < a.length && Array.prototype.push.apply(e, h.slice(1));
					k = h[0].length;
					j = m;
					if (e.length >= d)
						break
				}
				b.lastIndex === h.index && b.lastIndex++
			}
			if (j === a.length) {
				if (k || !b.test(""))
					e.push("")
			} else
				e.push(a.slice(j));
			return e.length > d ? e.slice(0, d) : e
		};
		n._compliantExecNpcg = /()??/.exec("")[1] === undefined;
		n._nativeSplit = String.prototype.split
	}
	String.prototype.split = function(a, b) {
		return n(this, a, b)
	}
})(jQuery);

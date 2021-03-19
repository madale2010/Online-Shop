function openOnImageClick(e) {
	var n = document.getElementById(e).src;
	document.getElementById("gallaryImage").src = n
}
function goBack() {
	window.history.back()
}
function reply_click(e) {
	window.location.href = e
}
!function(e, n, t, a, c, o, i) {
	e.GoogleAnalyticsObject = c, e[c] = e[c] || function() {
		(e[c].q = e[c].q || []).push(arguments)
	}, e[c].l = 1 * new Date, o = n.createElement(t), i = n
			.getElementsByTagName(t)[0], o.async = 1, o.src = a, i.parentNode
			.insertBefore(o, i)
}(window, document, "script", "https://www.google-analytics.com/analytics.js",
		"ga"), ga("create", "UA-81238110-1", "auto"), ga("send", "pageview");
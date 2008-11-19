<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<title>ezGestures</title>
	<link href="styles.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="main">
		<p class="title">ezGestures</p>
		<p><strong>ezGestures</strong> is a gesture recognition library for <a href="http://www.processing.org/">Processing</a>. It works by analyzing mouse or <a href="http://en.wikipedia.org/wiki/Wii_Remote">Wiimote</a> movements while dragging, and comparing the motion against a <a href="http://www.regular-expressions.info/">regular expression</a> to find a match. It was originally developed for <a href="http://www.silentlycrashing.net/tweetpad/">tweetPad</a>, but has been modified into a more general version for plugging into any project.</p>
		
		<p>The library understands four directions: UP, DOWN, LEFT, and RIGHT.<br/>
			It can be used to recognize:<br/>
			&nbsp;&nbsp;* the difference between a circle and an "L" shape.<br/>
			&nbsp;&nbsp;* the difference between a clockwise turn and a counter-clockwise turn.<br/>
			It CANNOT be used to recognize:<br/>
			&nbsp;&nbsp;* the difference between a square and a circle.<br/>
			&nbsp;&nbsp;* the area covered by a gesture.</p>
			
		<p>If you're going to analyze <a href="http://en.wikipedia.org/wiki/Wii_Remote">Wiimote</a> gestures, you'll need to install Andreas Schlegel's <a href="http://www.sojamo.de/libraries/oscP5/">oscP5</a> library, and you'll need to run his <a href="http://code.google.com/p/darwiinosc/">darwiinosc</a> application.</p>
		
		<p><span class="big">Download the <a href="http://ezjlibs.googlecode.com/files/ezgestures-0.40.zip">library</a></span><br/>
			version .40<br/>
			updated on Nov 19 2008<br/>
			unzip and drop the folder in /path/to/processing/libraries/</p>
		
		<p class="big">Consult the <a href="doc/">documentation</a></p>
		
		<p><span class="big">Browse the <a href="http://code.google.com/p/ezjlibs/source/browse/">source</a> on svn</span></p>
		
		<p><span class="big">Sketches</span><br/>
			&nbsp;&nbsp;* <a href="sketches/TheBlob/">The Blob</a><br/>
			&nbsp;&nbsp;* <a href="sketches/Figure8/">Figure 8</a></p>
			
		<p><strong>ezGestures</strong> is Open Source Software released under the <a href="http://www.fsf.org/licensing/licenses/gpl.html">GNU GPL</a>. It is developed by <a href="http://contact.silentlycrashing.net/?mailto=<?php echo base64_encode('ez@silentlycrashing.net'); ?>">Elie Zananiri</a>.</p>
	</div>
	
	<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
	</script>
	<script type="text/javascript">
	_uacct = "UA-1085473-5";
	urchinTracker();
	</script>
</body>

</html>

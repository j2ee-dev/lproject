<!DOCTYPE html>
<!--
Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.md or http://ckeditor.com/license
-->
<html>
<head>
	<meta charset="utf-8">
	<title>Using the CKEditor Read-Only API &mdash; CKEditor Sample</title>
	<script src="../../ckeditor.js"></script>
	<link rel="stylesheet" href="sample.css">
	<script>

		var editor;

		// The instanceReady event is fired, when an instance of CKEditor has finished
		// its initialization.
		CKEDITOR.on( 'instanceReady', function( ev ) {
			editor = ev.editor;

			// Show this "on" button.
			document.getElementById( 'readOnlyOn' ).style.display = '';

			// Event fired when the readOnly property changes.
			editor.on( 'readOnly', function() {
				document.getElementById( 'readOnlyOn' ).style.display = this.readOnly ? 'none' : '';
				document.getElementById( 'readOnlyOff' ).style.display = this.readOnly ? '' : 'none';
			});
		});

		function toggleReadOnly( isReadOnly ) {
			// Change the read-only state of the editor.
			// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-setReadOnly
			editor.setReadOnly( isReadOnly );
		}

	</script>
</head>
<body>
	<h1 class="samples">
		<a href="index.html">CKEditor Samples</a> &raquo; Using the CKEditor Read-Only API
	</h1>
	<div class="warning deprecated">
		This sample is not maintained anymore. Check out its <a href="http://sdk.ckeditor.com/samples/readonly.html">brand new version in CKEditor SDK</a>.
	</div>
	<div class="description">
		<p>
			This sample shows how to use the
			<code><a class="samples" href="http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-setReadOnly">setReadOnly</a></code>
			API to put editor into the read-only state that makes it impossible for logins to change the editor contents.
		</p>
		<p>
			For details on how to create this setup check the source code of this sample page.
		</p>
	</div>
	<form action="sample_posteddata.php" method="post">
		<p>
			<textarea class="ckeditor" id="editor1" name="editor1" cols="100" rows="10">&lt;p&gt;This is some &lt;strong&gt;sample text&lt;/strong&gt;. You are using &lt;a href="http://ckeditor.com/"&gt;CKEditor&lt;/a&gt;.&lt;/p&gt;</textarea>
		</p>
		<p>
			<input id="readOnlyOn" onclick="toggleReadOnly();" type="button" value="Make it read-only" style="display:none">
			<input id="readOnlyOff" onclick="toggleReadOnly( false );" type="button" value="Make it editable again" style="display:none">
		</p>
	</form>
	<div id="footer">
		<hr>
		<p>
			CKEditor - The text editor for the Internet - <a class="samples" href="http://ckeditor.com/">http://ckeditor.com</a>
		</p>
		<p id="copy">
			Copyright &copy; 2003-2016, <a class="samples" href="http://cksource.com/">CKSource</a> - Frederico
			Knabben. All rights reserved.
		</p>
	</div>
</body>
</html>

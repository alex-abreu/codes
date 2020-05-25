<?php
session_start();

?>
<?php

session_unset(); 
session_destroy(); 

?>

<?php 
echo "
            <script type=\"text/javascript\">
            window.location.href = 'http://alexabreu.dx.am/index.html';
            </script>
            ";

 ?>
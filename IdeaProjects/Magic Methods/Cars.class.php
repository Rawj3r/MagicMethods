<?php

/**
 * Created by IntelliJ IDEA.
 * User: Roger Nkosi
 * Date: 3/12/16
 * Time: 9:39 PM
 */
class Cars
{
    public $model;

    public function limou(){
        echo "This car is long";
    }

    function __get($name)
    {
        echo "$name does not exist";
    }

    function __set($name, $value)
    {
        echo "could not set $value to $name";
    }

    function __call($name, $arguments)
    {
        echo "cannot find $name($arguments)";
    }

    function __construct()
    {
        echo "I love Benz <br>";
    }

    function __toString()
    {
      return "";
    }

    function __clone()
    {

    }
}

$car = new Cars();
$car->limou();

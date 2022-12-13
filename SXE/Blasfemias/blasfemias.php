<?php
/**
 * @package Hello_Dolly
 * @version 1.7.2
 */
/*
Plugin Name: Blasfemias
Description: This is not just a plugin for replace vulgar words.
Author: Julián Lago
Version: 1.0
*/

function renym_wordpress_typo_fix($text)
{
    return str_replace('WordPress', 'WordPressDAM', $text);
}

function renym_caca_typo_fix($text)
{
    return str_replace('caca', 'popo', $text);
}

function renym_culo_typo_fix($text)
{
    return str_replace('culo', 'trasero', $text);
}

function renym_feo_typo_fix($text)
{
    return str_replace('feo', 'poco agraciado', $text);
}

function renym_polla_typo_fix($text)
{
    return str_replace('polla', 'pene', $text);
}

function renym_pedo_typo_fix($text)
{
    return str_replace('pedo', 'flatulencia', $text);
}

/**
 * Añadimos la función renym_wordpress_typo_fix al filtro 'the_content'
 * Se ejecutará cada vez que se cargue un post
 */
add_filter('the_content', 'renym_wordpress_typo_fix');
add_filter('the_content', 'renym_caca_typo_fix');
add_filter('the_content', 'renym_culo_typo_fix');
add_filter('the_content', 'renym_feo_typo_fix');
add_filter('the_content', 'renym_polla_typo_fix');
add_filter('the_content', 'renym_pedo_typo_fix');

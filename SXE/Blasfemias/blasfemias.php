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

function dB()
{

    global $wpdb;

    $charset_collate = $wpdb -> get_charset_collate();

    $blasfemias = $wpdb -> prefix . 'blasfemias';

    $tableCreate = "CREATE TABLE IF NOT EXISTS $blasfemias (
    id mediumint(9) NOT NULL,
    correctWord text NOT NULL,
    PRIMARY KEY (id)
    ) $charset_collate;";

    $sqlDlt = "DELETE FROM $blasfemias";
    $wpdb -> query($sqlDlt);
    require_once(ABSPATH . 'wp-admin/includes/upgrade.php');
    dbDelta($sqlDlt);

    $sqlIns = "INSERT INTO $blasfemias (id, correctWord)
    VALUES (1, 'popo'),
           (2,'trasero'),
           (3,'poco agraciado'),
           (4,'pene'),
           (5,'flatulencia');";

    require_once( ABSPATH . 'wp-admin/includes/upgrade.php' );
    dbDelta($tableCreate);
    dbDelta($sqlIns);

}

add_action('plugins_loaded', 'dB' );

function substitute_words( $text )
{

    $rudeWords = array("caca", "culo", "feo", "polla", "pedo");

    global $wpdb;

    $blasfemias = $wpdb -> prefix . 'blasfemias';

    $result = $wpdb -> get_results("SELECT correctWord FROM " . $blasfemias, ARRAY_A);

    foreach ($result as $row) {
        $correctWords[] = $row['palabrasSustitutas'];
    }

    return str_replace($rudeWords, $correctWords, $text);
}

//function renym_wordpress_typo_fix($text)
//{
//    return str_replace('WordPress', 'WordPressDAM', $text);
//}
//
//function renym_caca_typo_fix($text)
//{
//    return str_replace('caca', 'popo', $text);
//}
//
//function renym_culo_typo_fix($text)
//{
//    return str_replace('culo', 'trasero', $text);
//}
//
//function renym_feo_typo_fix($text)
//{
//    return str_replace('feo', 'poco agraciado', $text);
//}
//
//function renym_polla_typo_fix($text)
//{
//    return str_replace('polla', 'pene', $text);
//}
//
//function renym_pedo_typo_fix($text)
//{
//    return str_replace('pedo', 'flatulencia', $text);
//}

/**
 * Añadimos la función renym_wordpress_typo_fix al filtro 'the_content'
 * Se ejecutará cada vez que se cargue un post
 */

//add_filter('the_content', 'renym_wordpress_typo_fix');
//add_filter('the_content', 'renym_caca_typo_fix');
//add_filter('the_content', 'renym_culo_typo_fix');
//add_filter('the_content', 'renym_feo_typo_fix');
//add_filter('the_content', 'renym_polla_typo_fix');
//add_filter('the_content', 'renym_pedo_typo_fix');

register_activation_hook( __FILE__, 'tmosest_plugin_activation' );
function tmosest_plugin_activation() {
    add_post_meta(34, 'activated', '0', true);
}

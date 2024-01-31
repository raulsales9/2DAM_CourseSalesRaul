
/**
 * Javascript helper function for Folder module
 *
 * @package    mod
 * @subpackage folder
 * @copyright  2009 Petr Skoda  {@link http://skodak.org}
 * @license    http://www.gnu.org/copyleft/gpl.html GNU GPL v3 or later
 */
M.mod_folder={};M.mod_folder.init_tree=function(Y,id,expand_all){Y.use('yui2-treeview','node-event-simulate',function(Y){var tree=new Y.YUI2.widget.TreeView(id);tree.subscribe("clickEvent",function(node,event){return!1});tree.subscribe("enterKeyPressed",function(node){Y.one(node.getContentEl()).one('a').simulate('click');return!1});if(expand_all){tree.expandAll()}else{tree.getRoot().children[0].expand()}
tree.render()})}
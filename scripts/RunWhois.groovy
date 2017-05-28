/*
    FreeTransform, scripts that perform simple Maltego transformations.
    Copyright (C) 2017  Stjepan Groš

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
    The script is called upon an arbitrary node.
    Parameter is node text.
    The script works correctly on nodes that represent anything Whois can answer.
*/

// @ExecutionModes({on_single_node="node_popup_scripting/FreeTransform[addons.installer.title]"})
try {
	whoisNode = node.createChild()
	whoisNode.setText("Whois Output")
	cmd = "whois " + node.getPlainText()
	whoisNode.createChild().setText(cmd.execute().text)
}
catch (Exception e) {
       	ui.errorMessage(e.getMessage())
}

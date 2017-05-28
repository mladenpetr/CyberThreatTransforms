/*
    FreeTransform, scripts that perform simple Maltego transformations.
    Copyright (C) 2017  Mladen Petr

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
    The script works correctly on nodes that represent a domain name or an URL
    In case of a incorrect script call, it creates appropriate error message.
    The script uses VirusTotal API to get relevant information.
    You can get public VirusTotal API for free at https://www.virustotal.com/en/documentation/public-api/
*/

// @ExecutionModes({on_single_node="node_popup_scripting/FreeTransform[addons.installer.title]"})

import java.net.MalformedURLException
import java.net.URL
import me.vighnesh.api.virustotal.VirusTotalAPI

def VIRUS_TOTAL_API_KEY = "not configured"

if (VIRUS_TOTAL_API_KEY == "not configured") {
	ui.errorMessage("You have to provide VirusTotal key in order to use this script!")
	return
}

try {
	report = VirusTotalAPI.configure(VIRUS_TOTAL_API_KEY).getURLReport(node.getPlainText())
}
catch(UnknownHostException e) {
	ui.errorMessage(e.getMessage())
	return
}

virusTotalNode = node.createChild('VirusTotal Scan')
if (report.getPositives() > 0) {
	child = virusTotalNode.createChild('Total ' + report.getPositives() + ' positive identifications')
	child.link.text = "https://www.virustotal.com/en/url/" + report.getScanId().split("-")[0] + "/analysis"
} else {
	child = virusTotalNode.createChild('Non malicious')
}

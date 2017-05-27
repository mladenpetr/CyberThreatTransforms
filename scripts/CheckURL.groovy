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

import java.net.MalformedURLException
import java.net.URL
import me.vighnesh.api.virustotal.VirusTotalAPI
import me.vighnesh.api.virustotal.dao.URLScan
import me.vighnesh.api.virustotal.dao.URLScanReport

static main(args) throws MalformedURLException {

	def VIRUS_TOTAL_API_KEY = "not configured"

	if (VIRUS_TOTAL_API_KEY == "not configured") {
		ui.errorMessage("You have to provide VirusTotal key in order to use this script!")
		return
	}

	def report
	def url
	try{
		if (node.getPlainText().contains("http://") || node.getPlainText().contains("https://")){
			node.setText(node.getPlainText().replace("http://",""))
		}
		url=new URL("http://"+node.getPlainText())
		URLConnection conn = url.openConnection()   //These next two lines test the connection of the provided url. If its not valid, the exception will be thrown.
		conn.connect()
		VirusTotalAPI vt=VirusTotalAPI.configure(VIRUS_TOTAL_API_KEY)
		report=vt.getURLReport(url)
	}
	catch(UnknownHostException e) {
		ui.errorMessage(e.getMessage())
		return
	}

	child=node.createChild('Report:'+report.getPositives()+" found out of "+report.getTotal())
	child.link.text="https://www.virustotal.com/en/url/"+report.getScanId().split("-")[0]+"/analysis"
}

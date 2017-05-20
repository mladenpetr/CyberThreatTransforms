/*
FreeTransform, scripts that perform simple Maltego transformations.
Copyright 2017 Mladen Petr

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

/*
	The script is called upon an arbitrary node.
	Parameter is node text.
	The script works correctly on nodes that represent an IP address
	or a domain name.
	In case of a incorrect script call, it creates appropriate error message.
    The script uses GeoIP API. Get it here : http://dev.maxmind.com/geoip/legacy/downloadable/
*/

import com.maxmind.geoip.*

public static void main (String[] args){

	LookupService ls = new LookupService("E:\\FER\\GeoIP\\GeoLiteCity.dat",
		LookupService.GEOIP_MEMORY_CACHE ); // replace the string in this command with path to your GeoLiteCity database!
	def location=ls.getLocation(node.getPlainText())
	if (location==null) {
		ui.errorMessage('The node text is not an IP address nor a domain name!')
	}
	else {
		if (location.city==null){
			location.city='Unknown'
		}
		def child=node.createChild(location.city+","+location.countryName)
	}
}
	


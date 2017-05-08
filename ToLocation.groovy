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
	The script works correctly on nodes that represent an IP address
	or a domain name.
	In case of a incorrect script call, it creates appropriate error message.
    The script uses GeoIP API. Get it here : http://dev.maxmind.com/geoip/legacy/downloadable/
*/

import com.maxmind.geoip.*

public static void main (String[] args){

	LookupService cl = new LookupService("E:\\FER\\GeoIP\\GeoLiteCity.dat",
		LookupService.GEOIP_MEMORY_CACHE ); // replace the string in this command with path to your GeoLiteCity database!
	def location=cl.getLocation(node.getPlainText())
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
	


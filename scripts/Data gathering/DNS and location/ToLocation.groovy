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

// @ExecutionModes({on_all_selected_nodes="node_popup_scripting/FreeTransform[addons.installer.title]"})
import com.maxmind.geoip.*

def GEO_LITE_CITY_DB = "not configured"

if (GEO_LITE_CITY_DB == "not configured") {
        ui.errorMessage("You have to provide GeoLiteCity.dat path to the script!")
        return
}

LookupService ls = new LookupService(GEO_LITE_CITY_DB, LookupService.GEOIP_MEMORY_CACHE);

def location = ls.getLocation(node.getPlainText())
if (location != null)
{
	if (location.city != null)
	{
		node.createChild(location.city + ", " + location.countryName)
	} else
		location.city='Unknown'
} else
	ui.errorMessage('The node text is not an IP address nor a domain name!')	


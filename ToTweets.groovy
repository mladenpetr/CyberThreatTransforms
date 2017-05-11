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
The scripts runs a search using twitter API and returns latest 20 tweets of the user.
If node text does not represent a existing username, the user is properly informed.
Results are shown with attached link to belonging tweets.
If nothing is found, the user is properly notified.
*/

import twitter4j.*
import twitter4j.conf.ConfigurationBuilder

static void main(String[] args) {

	ConfigurationBuilder cb = new ConfigurationBuilder()
	cb.setDebugEnabled(true).setOAuthConsumerKey("CONSUMER_KEY").setOAuthConsumerSecret("CONSUMER_SECRET").setOAuthAccessToken("ACCESS_TOKEN").setOAuthAccessTokenSecret("ACCESS_TOKEN_SECRET")
	TwitterFactory tf = new TwitterFactory(cb.build())
	Twitter twitter = tf.getInstance()
	def username=node.getPlainText().split('@')[1].split(',')[0]
	def timeline
	try {
		timeline=twitter.getUserTimeline(username)
	}
	catch(Exception e) {
		ui.errorMessage('The node text is not an existing username!')
		return
	}
	if (timeline.size()==0){
		ui.informationMessage('The User doesn\'t have any tweets')
	}
	else {
		for (Status status:timeline){
			def child=node.createChild()
			child.setText(status.getText())
			child.link.text='https://twitter.com/'+node.getPlainText()+"/status/"+status.getId()
		}
	}

}
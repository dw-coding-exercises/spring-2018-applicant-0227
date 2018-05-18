# Submission to Democracy Works

First of all, thank you for the opportunity to work on this app. I had never worked with Clojure before so it was a lot of fun ting to write in a different language.

### App description
I have completed the required asks for the project, namely:
* Create the missing /search route
* Ingest the incoming form parameters
* Derive a basic set of OCD-IDs from the address (see below for further explanation)
* Retrieve upcoming elections from the Democracy Works election API using those OCD-IDs
* Display any matching elections to the user

### Code added by interviewee

Code for making the necessary api call can be found in `src/my_exercise/api.clj`

Code for displaying the result to the user in `/search` can be found in `src/my_exercise/search.clj`

Code for performing unit tests can be found in `test/my_exercise/api_test.clj`
>Attempted to add brower-based test but did not have time

README.md added as an attempt to document code

### Updates made in `home.clj`

Following updates made to `src/my_exercise/home.clj`:
>* All input fields in the form (including select tag) is now required through the use to `required` html tag
>* Hash (*) added in form fields to show user that fields are required (refer to css file)
>* All form fields have default values; this was done for testing purposes and can be changed

From the current elections page:
> https://github.com/democracyworks/dw-code-exercise-lein-template/wiki/Current-elections

I notiecd that (except for one election in Norwell, MA) all elections only use two OCD-IDs: country and state.

As the only country is US, I have hardcoded the value in the `my-exercise.api/get-results` function. The state is then pulled from the user input.

Please note that my submission will not work for 'place' for the reason that only 1 place has upcoming election. I had updated `get-results` for show current elections when place is "Norwell" (`ocd-division/country:us/state:ma/place:norwell`) but felt it was hacky, so removed it. I have shown below how I would've improved the function if given more time.

### Possible improvements
Given more time, I would make my `get-results` function recursive in the following way:

1. Start with the smallest OCD division ID that can have upcoming elections (i.e., place/city) and check the api for any current elections
2. Store that result for the smallest OCD division ID
3. Recur 'get-results' to get upcoming elections for the next largest OCD division ID (i.e. state)
4. Store the results for the state
5. Parse through the stored results for place and state to display all relevant elections

I would also add browser-based unit tests in `test/my_exercise/browser_test.clj`

> Note: As all upcoming elections are at the state level , except for "Norwell" in MA which is a place, I have ignored it for the purpose of the submission. 

Please refer to comments inside the relevant functions for more information!
; This namespace contains the api calls to the http calls made to the API
(ns my-exercise.api
  (:require [clj-http.client :as client]
            [clojure.string :as string]
            [clojure.edn :as edn]))

(defn get-results 
  "Function calls the api with the user entered value for state"
  [address]
  (let [url "https://api.turbovote.org/elections/upcoming" ;fixed url that is same for all requests
        query "?district-divisions=ocd-division/country:us/state:" ;as country is fixed all well, this was set as a string
        state-params (get address :state) ;state parameter derived from user input
        api-address (string/lower-case (str url query state-params)) ;api-address combines all previous 3 url values
        response (:body (client/get (str api-address))) ;fetches election info from api-address; only :body key is extracted as this contains all the info we need
        response-list (edn/read-string response) ;string "response" converted to edn
        response-array-map (first response-list) ;first item (in array-map format) from edn extracted
        election-name (:description response-array-map) ; :description key showing name of election
        election-date (:date response-array-map) ; :date key showing date of election
        election-website (:polling-place-url-shortened response-array-map)] ; shoetened url extracted as this is easy to show on the user page
        (if (nil? election-name) 
          nil ; if no election is returned; the the front-end get nil and show appropriate page to user
          [election-name (.format (java.text.SimpleDateFormat. "MM/dd/yyyy") election-date) election-website]))) ; if upcoming election exists, relevant details are sent to front-end as vector

(def my-str "hello")
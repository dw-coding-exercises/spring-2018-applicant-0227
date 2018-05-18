(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [my-exercise.home :as home]
            [my-exercise.api :as api]))

(defn go-home 
  "Function goes underneath the search result (both when there is search result and when none) that allows user to go back to home page"
  []
  [:h4 [:a {:href "/"} "Click here"] " to go back to the home page for a different search!"])

(defn no-result-page 
  "Function is served when there are no upcoming elections in the user's state and the api call returns 'nil'"
  []
  [:div {:class "result" :align "center"}
  [:h2 {:class "election"} "You do not have any elections in your state at the moment!"]
  (go-home)])

(defn display-result-page 
  "Function is served when user's input state has upcoming election as retrned by the api - lists the election name, date, and website
  As all the api calls to current OCD-IDs return one election, this has been set up to only show one election; had more than one election been returned, I would have set this differently"
  [result]
  (let [[name date website] result]
  [:div {:class "result" :align "center"}
  [:h1 {:class "election"} "You have an upcoming election in your state"]
  [:h2 {:class "election-name"} [:u "Upcoming election:"] name]
  [:h3 {:class "election-date"} [:u "Date of election:"] date]
  [:h3 {:class "election-website"} [:u "Visit for more details:"] [:a {:href website :target "_blank"} website]]
  (go-home)]))

(defn search-result 
  "Function shows the appropriate page to user depending on if there is any upcoming election or not"
  [request]
  (def result (api/get-results request))
  [:div {:class "search-result"}
    (if (nil? result)
      (no-result-page)
      (display-result-page result))])

(defn search-result-page 
  "Function is called after the user enter into the input field and is direction to '/search' page"
  [request]
  (html5
    (home/header request)
    (search-result request)))
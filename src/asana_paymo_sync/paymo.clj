(ns asana-paymo-sync.paymo
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]
            [asana-paymo-sync.config :as config]))

(defn paymo-get
  "Wrapper to include the authentication header for GET calls"
  [url]
  (-> (client/get (str (:api-url config/paymo)
                       url)
                  {:basic-auth [(:username config/paymo) (:password config/paymo)]
                   :follow-redirects true
                   :accept :json})
      :body
      (parse-string true)))

(defn paymo-post
  "Wrapper to include the authentication header for POST calls"
  [url data]
  (-> (client/post (str (:api-url config/paymo)
                        url)
                   {:basic-auth [(:username config/paymo) (:password config/paymo)]
                    :accept :json
                    :form-params data
                    :content-type :json})
      :body
      (parse-string true)))

(defn clients
  "Retrieve clients from paymo"
  []
  (paymo-get "clients"))

(defn users
  "Retrieve users from paymo"
  []
  (paymo-get "users"))

(defn projects
  "Retrieve projects from paymo"
  []
  (paymo-get "projects"))

(defn project
  "Retrieves a project with its tasklists and tasks from paymo"
  [project-id]
  (->> (str "projects/"
            project-id
            "?include=tasklists,tasks")
       paymo-get
       :projects
       first))

(defn create-tasklist
  [name project-id]
  (paymo-post "tasklists"
              {:name name
               :project_id project-id}))

(defn create-task
  [name tasklist-id project-id]
  (paymo-post "tasks"
              {:name name
               :tasklist_id tasklist-id
               :project_id project-id}))


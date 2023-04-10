from locust import HttpUser, task
from cpf_generator import CPF
import random

class HelloWorldUser(HttpUser):

    session_id = None

    @task
    def vote(self):
        id = self.session_id
        self.client.post("/session/{}/vote".format(id), json={"vote": random.choice(["YES", "NO"]),
                                                                  "associate": {
                                                                    "cpf": CPF.generate()
                                                                  }})
        

    def on_start(self):
        response_topic = self.client.post("/topic", json={"title":"test", "description":"test"}).json()
        print("--------------------------------")
        print(response_topic)
        topic_id = response_topic["id"]
        response_session = self.client.post("/session/open", json={"topicId" : topic_id, "timeToVote": 5}).json()
        print("--------------------------------")
        print(response_session)
        self.session_id = response_session["id"]
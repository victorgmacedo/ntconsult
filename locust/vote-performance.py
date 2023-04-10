from locust import HttpUser, task
from cpf_generator import CPF
import random

class Vote(HttpUser):

    topic_id = None

    @task
    def vote(self):
        self.client.post("/topics/{}/session/vote".format(self.topic_id), json={"vote": random.choice(["YES", "NO"]),
                                                                                "associate": {
                                                                                    "cpf": CPF.generate()
                                                                                }})


    def on_start(self):
        response_topic = self.client.post("/topics", json={"title":"test", "description":"test"}).json()
        self.topic_id = response_topic["id"]
        response_session = self.client.post("/topics/{}/session/open".format(self.topic_id), json={"timeToVote": 5})
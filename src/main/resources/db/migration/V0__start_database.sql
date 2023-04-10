CREATE TABLE topic (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    title varchar(100),
    description varchar(500)
);

CREATE TABLE result_detail(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    total_votes int,
    result varchar(3),
    vote_detail json
);

CREATE TABLE "session" (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    topic_id uuid,
    result_id uuid,
    finish_at TIMESTAMP WITH TIME ZONE,
    started_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_topic FOREIGN KEY(topic_id) REFERENCES topic(id),
    CONSTRAINT fk_result_detail FOREIGN KEY(result_id) REFERENCES result_detail(id)
);

CREATE TABLE associate(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    cpf varchar(11)
);

CREATE TABLE session_vote(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    session_id uuid,
    associate_id uuid,
    vote varchar(3),
    CONSTRAINT fk_session FOREIGN KEY(session_id) REFERENCES "session"(id),
    CONSTRAINT fk_associate FOREIGN KEY(associate_id) REFERENCES associate(id)
);


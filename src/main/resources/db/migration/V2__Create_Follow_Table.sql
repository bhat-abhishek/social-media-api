CREATE TABLE follow (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    follower_id UUID NOT NULL,
    followee_id UUID NOT NULL,
    CONSTRAINT fk_follower FOREIGN KEY (follower_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_followee FOREIGN KEY (followee_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT unique_follow UNIQUE (follower_id, followee_id)
);

CREATE INDEX idx_follow_follower ON follow(follower_id);
CREATE INDEX idx_follow_followee ON follow(followee_id);
class Musician < ApplicationRecord
    has_many :posts, as: :postable
    has_many :jobs
end

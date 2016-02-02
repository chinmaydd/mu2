class User < ApplicationRecord
	has_many :posts, as: :postable
end

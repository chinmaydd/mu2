class Musician < ApplicationRecord
	has_many :performances, as: :performable
end

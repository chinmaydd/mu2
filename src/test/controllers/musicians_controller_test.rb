require 'test_helper'

class MusiciansControllerTest < ActionDispatch::IntegrationTest
  setup do
    @musician = musicians(:one)
  end

  test "should get index" do
    get musicians_url
    assert_response :success
  end

  test "should create musician" do
    assert_difference('Musician.count') do
      post musicians_url, params: { musician: { contact_number: @musician.contact_number, description: @musician.description, name: @musician.name } }
    end

    assert_response 201
  end

  test "should show musician" do
    get musician_url(@musician)
    assert_response :success
  end

  test "should update musician" do
    patch musician_url(@musician), params: { musician: { contact_number: @musician.contact_number, description: @musician.description, name: @musician.name } }
    assert_response 200
  end

  test "should destroy musician" do
    assert_difference('Musician.count', -1) do
      delete musician_url(@musician)
    end

    assert_response 204
  end
end

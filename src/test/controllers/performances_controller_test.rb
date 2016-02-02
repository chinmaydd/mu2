require 'test_helper'

class PerformancesControllerTest < ActionDispatch::IntegrationTest
  setup do
    @performance = performances(:one)
  end

  test "should get index" do
    get performances_url
    assert_response :success
  end

  test "should create performance" do
    assert_difference('Performance.count') do
      post performances_url, params: { performance: { date: @performance.date, description: @performance.description, link: @performance.link } }
    end

    assert_response 201
  end

  test "should show performance" do
    get performance_url(@performance)
    assert_response :success
  end

  test "should update performance" do
    patch performance_url(@performance), params: { performance: { date: @performance.date, description: @performance.description, link: @performance.link } }
    assert_response 200
  end

  test "should destroy performance" do
    assert_difference('Performance.count', -1) do
      delete performance_url(@performance)
    end

    assert_response 204
  end
end

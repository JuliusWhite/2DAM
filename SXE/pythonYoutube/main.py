from pytube import YouTube
from reportlab.lib.pagesizes import A4
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle
import psycopg2

yt = YouTube("https://www.youtube.com/watch?v=sGGIgxL0OlY")

# Title of video
print("Title: ", yt.title)
# Number of views of video
print("Number of views: ", yt.views)
# Length of the video
print("Length of video: ", yt.length, "seconds")
# Description of video
print("Description: ", yt.description)
# Rating
print("Ratings: ", yt.rating)


import os
import glob
import numpy as np

import scipy.io.wavfile


CHART_DIR = os.path.join(".", "charts") 
FFT_DIR = "D:\Project\FFT_BASE"   #model of data
GENRE_DIR = "D:\\History_Project\\genres"   #100 files for each genre

GENRE_LIST = ["blues","classical", "jazz", "country", "pop", "rock", "metal","disco","reggae","hiphop"]


def write_fft(fft_features, fn, out_dir=FFT_DIR):
    """
    Write the FFT features to separate files to speed up processing.
    """
    path, base_fn = os.path.split(fn)
    path, genre = os.path.split(path)
    data_fn = base_fn + ".fft"  #make file name extension .fft
    FFT_DIR_GENRE = os.path.join(out_dir, genre);
    np.save(os.path.join(FFT_DIR_GENRE, data_fn), fft_features)
    print (data_fn)

def create_fft(fn):
    """
    Create FFT features from the specified file, fn. 
    (receives, and also passes to write_fft(), an absolute path to fn)
    """
    sample_rate, X = scipy.io.wavfile.read(fn)

    fft_features = abs(scipy.fft(X)[:1000])
    write_fft(fft_features, fn)

def store_fft_all(genre_list=GENRE_LIST, base_dir=GENRE_DIR, out_dir=FFT_DIR):
    for label, genre in enumerate(genre_list):
        genre_dir = os.path.join(base_dir, genre, "*.wav")
        file_list = glob.glob(genre_dir)
        #print file_list[-1]
        for file in file_list:
            #path, file = os.path.split(file)
            create_fft(file)
            words = file.split("\\")
            print (words[-1])
            
        print (label)
    


def read_fft(genre_list=GENRE_LIST, base_dir=GENRE_DIR):
    """
    Reads the FFT features and labels into numpy arrays and returns them
    """
    X = []
    y = []
    for label, genre in enumerate(genre_list):
        print(genre)
        genre_dir = os.path.join(base_dir, genre, "*.fft.npy")
        file_list = glob.glob(genre_dir)
        assert(file_list), genre_dir
        for fn in file_list:
            fft_features = np.load(fn)

            X.append(fft_features[:2000])
            y.append(label)

    return np.array(X), np.array(y)


if __name__ == "__main__":
#     genres = ["classical", "jazz", "country", "pop", "rock", "metal"]
#     print genres
    store_fft_all()

import numpy as np
import sys
from collections import defaultdict

from sklearn.metrics import precision_recall_curve, roc_curve
from sklearn.metrics import auc
from sklearn.cross_validation import ShuffleSplit

from sklearn.metrics import confusion_matrix

from utils import plot_pr, plot_roc, plot_confusion_matrix, GENRE_LIST
from sklearn.linear_model.logistic import LogisticRegression

TEST_DIR = "D:\History_Project\genres\test" 
FFT_DIR = "D:\Project\FFT_BASE"  

genre_list = GENRE_LIST

from fft import read_fft, write_fft, create_fft
class Model:
    lr = LogisticRegression();
    def _init_(self):
        lr = LogisticRegression()
        
       
        
    




def train_model(clf_factory, X, Y, name, plot=False):
    labels = np.unique(Y)
    print(labels)

    cv = ShuffleSplit(
        n=len(X), n_iter=1, test_size=0.3,  random_state=0)

    train_errors = []
    test_errors = []

    scores = [] #all scores 
    pr_scores = defaultdict(list) #precision  scores
    precisions, recalls, thresholds = defaultdict(
        list), defaultdict(list), defaultdict(list)

    roc_scores = defaultdict(list)
    tprs = defaultdict(list)#true positives
    fprs = defaultdict(list)#false positives

    clfs = []  # just to later get the median

    cms = [] # 

    for train, test in cv:
        X_train, y_train = X[train], Y[train]
        X_test, y_test = X[test], Y[test]
        print(X_test)
        clf = clf_factory#create classifier based on logistic regression()
        clf.fit(X_train, y_train)#process of training
        clfs.append(clf) #add values for obtainint median

        train_score = clf.score(X_train, y_train) # get trainint value 
        test_score = clf.score(X_test, y_test)# get test values 
        scores.append(test_score)

        train_errors.append(1 - train_score)
        test_errors.append(1 - test_score)

        y_pred = clf.predict(X_test)#what I actually need (prediciton )
        cm = confusion_matrix(y_test, y_pred)#confusion matix to evaluate a clsssfier 
        cms.append(cm)

        for label in labels:
            y_label_test = np.asarray(y_test == label, dtype=int)
            proba = clf.predict_proba(X_test)
            print(proba)
            proba_label = proba[:, label]

            precision, recall, pr_thresholds = precision_recall_curve(
                y_label_test, proba_label)#based on y_label_test (a priori known),and proba_labe(discovered));
            pr_scores[label].append(auc(recall, precision))
            precisions[label].append(precision)
            recalls[label].append(recall)
            thresholds[label].append(pr_thresholds)

            fpr, tpr, roc_thresholds = roc_curve(y_label_test, proba_label)
            roc_scores[label].append(auc(fpr, tpr))
            tprs[label].append(tpr)
            fprs[label].append(fpr)

    if plot:
        for label in labels:
            print ( genre_list[label])
            scores_to_sort = roc_scores[label]
            median = np.argsort(scores_to_sort)[len(scores_to_sort) / 2]

            desc = "%s %s" % (name, genre_list[label])
            plot_pr(pr_scores[label][median], desc, precisions[label][median],
                    recalls[label][median], label='%s vs rest' % genre_list[label])
            plot_roc(roc_scores[label][median], desc, tprs[label][median],
                     fprs[label][median], label='%s vs rest' % genre_list[label])

    all_pr_scores = np.asarray(pr_scores.values()).flatten()
    
   
          
    #summary = (np.mean(scores), np.std(scores),np.mean(all_pr_scores)), np.std(all_pr_scores)
   

    return np.mean(train_errors), np.mean(test_errors), np.asarray(cms)


def create_model():
    from sklearn.linear_model.logistic import LogisticRegression
    clf = LogisticRegression()

    return clf

def main():
 model = Model()

 if len(sys.argv) > 1:
     print(1)
     filename = sys.argv[1];
     create_fft(filename,TEST_DIR)
     X  = read_fft(TEST_DIR)  
     for test in X:
         X_test = X[test]
         #y_pred = model.clf.predict(X_test)
         maxval=0;
         maxindex=0;
         for label  ,genre in enumerate(genre_list):
             proba = model.clf.predict_proba(X_test)
             if maxval > proba :
                 maxval = proba;
                 maxindex[label];
             else:
                 print filename,genre_list[maxindex]      
            
            
             
         
         
         
     
 if len(sys.argv) == 1:
     print "2"
     
     
     
     print "reading"
     X, y = read_fft(genre_list, FFT_DIR)
     print "Training"
     train_avg, test_avg, cms = train_model(model.lr, X, y, "Log Reg FFT", plot=True)
     print "Computing averages"
     cm_avg = np.mean(cms, axis=0)
     cm_norm = cm_avg / np.sum(cm_avg, axis=0)

     print (cm_norm)
      print "Plottin confusion matrix"
     plot_confusion_matrix(cm_norm, genre_list, "fft", "Confusion matrix of an FFT based classifier")

if __name__ == "__main__":
    main() 

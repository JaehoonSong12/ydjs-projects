###### source ../venv/Scripts/activate
###### python std01-jayden-stockpredict/main.py

# main.py
#  Need to install:
#    pip install yfinance pandas scikit-learn matplotlib

import os
from typing import List, Tuple

import yfinance as yf
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from pathlib import Path
import matplotlib.pyplot as plt


# ----------------------------------------
#  Constants / Configuration
# ----------------------------------------
TICKERS   = ["AAPL", "MSFT", "GOOG"]   # ← Customize this list
PERIOD    = "5y"                       # ← How far back to pull data
INTERVAL  = "1d"                       # ← Daily bars
TEST_SIZE = 0.80                       # ← 20% of data used for testing
OUTPUT_DIR = Path("std01-jayden-stockpredict/plots")  # ← Where PNGs will be saved


# ----------------------------------------
#  1. Utility: Ensure output directory exists
# ----------------------------------------
def ensure_output_dir(path: str) -> None:
    if not os.path.isdir(path):
        os.makedirs(path, exist_ok=True)


# ----------------------------------------
#  2. Download OHLC data for one ticker
# ----------------------------------------
def fetch_price_data(
    ticker: str, 
    period: str = PERIOD, 
    interval: str = INTERVAL,
    auto_adjust: bool = False
) -> pd.DataFrame:
    """
    Downloads historical OHLC data for a single ticker.
    Returns a DataFrame with columns ['High', 'Low', 'Close'] and a DatetimeIndex.
    """
    df = yf.download(ticker, period=period, interval=interval, auto_adjust=auto_adjust)
    # Keep only the columns we need
    return df[["High", "Low", "Close"]]


# ----------------------------------------
#  3. Take raw OHLC and create features + target
# ----------------------------------------
def prepare_features(
    df: pd.DataFrame
) -> Tuple[pd.DataFrame, pd.Series]:
    """
    Given a DataFrame with ['High', 'Low', 'Close'], 
    attach a 'Target' column = next-day Close, drop NaNs, 
    and return (X, y) where X = DataFrame[['High', 'Low']], y = Series(Target).
    """
    df = df.copy()
    df["Target"] = df["Close"].shift(-1)
    df = df.dropna()  # removes the final row where 'Target' is NaN
    X = df[["High", "Low"]]
    y = df["Target"]
    return X, y


# ----------------------------------------
#  4. Train a simple LinearRegression model
# ----------------------------------------
def train_regression(
    X: pd.DataFrame, 
    y: pd.Series, 
    test_size: float = TEST_SIZE
) -> Tuple[LinearRegression, pd.DataFrame, pd.Series, pd.DataFrame, pd.Series]:
    """
    Splits X/y into train/test (chronological split: shuffle=False),
    fits a LinearRegression on the train set, and returns:
      (model, X_train, y_train, X_test, y_test)
    """
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=test_size, shuffle=False
    )
    model = LinearRegression()
    model.fit(X_train, y_train)
    return model, X_train, y_train, X_test, y_test


# ----------------------------------------
#  5. Predict next-day Close for the “most recent” row
# ----------------------------------------
def predict_next_close(
    model: LinearRegression, 
    X: pd.DataFrame
) -> float:
    """
    Takes a trained model and the full feature‐matrix X,
    selects only the very last row of X, and returns model.predict.
    """
    latest_row = X.iloc[[-1]]    # keeps index structure so .index later is correct
    return model.predict(latest_row)[0]


# ----------------------------------------
#  6. Plot actual vs. predicted on the test set
# ----------------------------------------
def plot_actual_vs_predicted(
    ticker: str,
    dates: pd.DatetimeIndex,
    actual: pd.Series,
    predicted: pd.Series,
    output_dir: str = OUTPUT_DIR
) -> None:
    """
    Saves a PNG file (named {ticker}_regression.png) comparing actual vs. predicted.
    """
    plt.figure(figsize=(12, 6))
    plt.plot(dates, actual, label="Actual Close")
    plt.plot(dates, predicted, label="Predicted Close", linestyle="--")
    plt.xlabel("Date")
    plt.ylabel("Close Price (USD)")
    plt.title(f"{ticker}: Actual vs Predicted Close (Test Set)")
    plt.legend()
    plt.xticks(rotation=45)
    plt.tight_layout()

    filepath = os.path.join(output_dir, f"{ticker}_regression.png")
    plt.savefig(filepath)
    plt.close()
    print(f"  • Plot saved: {filepath}")


# ----------------------------------------
#  7. Encapsulate end-to-end logic in a class
# ----------------------------------------
class AssetPredictor:
    def __init__(self, ticker: str):
        self.ticker = ticker
        self.raw_df = None    # Raw OHLC
        self.X = None         # Features (High, Low)
        self.y = None         # Target (next-day Close)
        self.model: LinearRegression = None
        self.X_train = self.X_test = None
        self.y_train = self.y_test = None

    def run(self):
        # 1) Fetch
        print(f"\n--- Processing {self.ticker} ---")
        self.raw_df = fetch_price_data(self.ticker)

        # 2) Print a quick head/tail to confirm
        print("  Raw data sample (head):")
        print(self.raw_df.head().to_string())
        print("  Raw data sample (tail):")
        print(self.raw_df.tail().to_string())

        # 3) Prepare features/target
        self.X, self.y = prepare_features(self.raw_df)
        print(f"  Feature matrix shape: {self.X.shape}")
        print(f"  Target vector shape:  {self.y.shape}")

        # 4) Train/test split + fit
        (self.model, 
         self.X_train, self.y_train, 
         self.X_test,  self.y_test) = train_regression(self.X, self.y)
        print(f"  Trained LinearRegression on {self.X_train.shape[0]} rows, "
              f"tested on {self.X_test.shape[0]} rows.")

        # 5) Predict “tomorrow”
        next_close = predict_next_close(self.model, self.X)
        print(f"  Predicted close for tomorrow: ${next_close:.2f}")

        # 6) Plot test‐set actual vs. predicted
        y_pred_test = self.model.predict(self.X_test)
        plot_actual_vs_predicted(
            self.ticker,
            dates=self.X_test.index,
            actual=self.y_test,
            predicted=pd.Series(y_pred_test, index=self.X_test.index)
        )


# ----------------------------------------
#  8. Main entrypoint
# ----------------------------------------
def main(tickers: List[str]):
    ensure_output_dir(OUTPUT_DIR)

    for ticker in tickers:
        predictor = AssetPredictor(ticker)
        predictor.run()


if __name__ == "__main__":
    main(TICKERS)


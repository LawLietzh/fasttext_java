package fasttext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class Matrix {

	public float[][] data_ = null;
	public int m_ = 0; // vocabSize
	public int n_ = 0; // layer1Size

	public Matrix() {
	}

	public Matrix(int m, int n) {
		m_ = m;
		n_ = n;
		data_ = new float[m][n];
	}

	public Matrix(final Matrix other) {
		m_ = other.m_;
		n_ = other.n_;
		data_ = new float[m_][n_];
		for (int i = 0; i < m_; i++) {
			for (int j = 0; j < n_; j++) {
				data_[i][j] = other.data_[i][j];
			}
		}
	}

	public void zero() {
		for (int i = 0; i < m_; i++) {
			for (int j = 0; j < n_; j++) {
				data_[i][j] = 0.0f;
			}
		}
	}

	public void uniform(float a) {
		Random random = new Random(1l);
		for (int i = 0; i < m_; i++) {
			for (int j = 0; j < n_; j++) {
				data_[i][j] = Utils.randomFloat(random, -a, a);
			}
		}
	}

	public void addRow(final Vector vec, int i, float a) {
		Utils.checkArgument(i >= 0);
		Utils.checkArgument(i < m_);
		Utils.checkArgument(vec.m_ == n_);
		for (int j = 0; j < n_; j++) {
			data_[i][j] += a * vec.data_[j];
		}
	}

	public float dotRow(final Vector vec, int i) {
		Utils.checkArgument(i >= 0);
		Utils.checkArgument(i < m_);
		Utils.checkArgument(vec.m_ == n_);
		float d = 0.0f;
		for (int j = 0; j < n_; j++) {
			d += data_[i][j] * vec.data_[j];
		}
		return d;
	}

	public void load(InputStream input) throws IOException {
		IOUtil ioutil = new IOUtil();

		m_ = (int) ioutil.readLong(input);
		n_ = (int) ioutil.readLong(input);

		ioutil.setFloatArrayBufferSize(n_);
		data_ = new float[m_][n_];
		for (int i = 0; i < m_; i++) {
			ioutil.readFloat(input, data_[i]);
		}
	}

	public void save(OutputStream ofs) throws IOException {
		IOUtil ioutil = new IOUtil();
		ioutil.setFloatArrayBufferSize(n_);
		ofs.write(ioutil.longToByteArray(m_));
		ofs.write(ioutil.longToByteArray(n_));
		for (int i = 0; i < m_; i++) {
			ofs.write(ioutil.floatToByteArray(data_[i]));
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Matrix [data_=");
		if (data_ != null) {
			builder.append("[");
			for (int i = 0; i < m_ && i < 10; i++) {
				for (int j = 0; j < n_ && j < 10; j++) {
					builder.append(data_[i][j]).append(",");
				}
			}
			builder.setLength(builder.length() - 1);
			builder.append("]");
		} else {
			builder.append("null");
		}
		builder.append(", m_=");
		builder.append(m_);
		builder.append(", n_=");
		builder.append(n_);
		builder.append("]");
		return builder.toString();
	}

}

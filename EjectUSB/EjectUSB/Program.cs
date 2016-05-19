using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.InteropServices;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace EjectUSB
{
	static class Program
	{
		const int OPEN_EXISTING = 3;
		const uint GENERIC_READ = 0x80000000;
		const uint GENERIC_WRITE = 0x40000000;
		const uint IOCTL_STORAGE_EJECT_MEDIA = 0x2D4808;

		[System.Runtime.InteropServices.DllImport("kernel32")]
		private static extern int CloseHandle(IntPtr handle);

		[System.Runtime.InteropServices.DllImport("kernel32")]
		private static extern int DeviceIoControl
			(IntPtr deviceHandle, uint ioControlCode,
			  IntPtr inBuffer, int inBufferSize,
			  IntPtr outBuffer, int outBufferSize,
			  ref int bytesReturned, IntPtr overlapped);

		[System.Runtime.InteropServices.DllImport("kernel32")]
		private static extern IntPtr CreateFile
			(string filename, uint desiredAccess,
			  uint shareMode, IntPtr securityAttributes,
			  int creationDisposition, int flagsAndAttributes,
			  IntPtr templateFile);

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args)
		{
			if (args.Length > 0)
			{
                string s = args[0];
				EjectDrive(s);
			}
			else MessageBox.Show("Oops, I can't do this","Error",MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
		}

		static void EjectDrive(string driveLetter)
		{
			string path = "\\\\.\\" + driveLetter;

			IntPtr handle = CreateFile(path, GENERIC_READ | GENERIC_WRITE, 0,
				IntPtr.Zero, OPEN_EXISTING, 0, IntPtr.Zero);

			if ((long)handle == -1)
			{
                MessageBox.Show("USB устройство не может быть извлечено!",
         "Извлечение USB накопителей", MessageBoxButtons.OK, MessageBoxIcon.Information);
                MessageBox.Show(new Win32Exception(Marshal.GetLastWin32Error()).Message);
                return;
            }
			int dummy = 0;
			DeviceIoControl(handle, IOCTL_STORAGE_EJECT_MEDIA, IntPtr.Zero, 0,
				IntPtr.Zero, 0, ref dummy, IntPtr.Zero);
			int returnValue = DeviceIoControl(handle, IOCTL_STORAGE_EJECT_MEDIA,
					 IntPtr.Zero, 0, IntPtr.Zero, 0, ref dummy, IntPtr.Zero);
			CloseHandle(handle);
			MessageBox.Show("USB устройство может быть извлечено!",
		 "Извлечение USB накопителей", MessageBoxButtons.OK, MessageBoxIcon.Information);
		}
	}
}
